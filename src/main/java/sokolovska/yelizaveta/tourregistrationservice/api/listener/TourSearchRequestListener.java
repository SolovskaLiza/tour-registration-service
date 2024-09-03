package sokolovska.yelizaveta.tourregistrationservice.api.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import sokolovska.yelizaveta.tourregistrationservice.config.RabbitMqConfiguration;
import sokolovska.yelizaveta.tourregistrationservice.model.SearchQuery;
import sokolovska.yelizaveta.tourregistrationservice.model.TourDto;
import sokolovska.yelizaveta.tourregistrationservice.service.TourSearchService;
import sokolovska.yelizaveta.tourregistrationservice.service.mapper.SearchQueryMapper;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Component
public class TourSearchRequestListener {
    private final TourSearchService tourSearchService;
    private final SearchQueryMapper queryMapper;
    private final RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = RabbitMqConfiguration.TOUR_SEARCH_REQUEST_QUEUE)
    public void listenTourResearchRequest(Message searchQuery) throws IOException {
            log.info("Message:" + searchQuery.toString());
            SearchQuery query = queryMapper.readBytes(searchQuery.getBody());

            Object correlationID = searchQuery.getMessageProperties().getHeaders().get("correlationId");

            List<TourDto> resultOfSearching = tourSearchService.search(query);
            Message searchingResult = MessageBuilder
                    .withBody(queryMapper.convertQueryResult(resultOfSearching))
                    .setHeader("correlationId", correlationID)
                    .build();
            rabbitTemplate.send(RabbitMqConfiguration.TOUR_SEARCH_RESPONSE_QUEUE, searchingResult);
    }
}
