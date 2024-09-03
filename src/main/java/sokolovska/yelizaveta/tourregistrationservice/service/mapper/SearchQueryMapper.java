package sokolovska.yelizaveta.tourregistrationservice.service.mapper;

import sokolovska.yelizaveta.tourregistrationservice.model.SearchQuery;
import sokolovska.yelizaveta.tourregistrationservice.model.TourDto;

import java.io.IOException;
import java.util.List;

public interface SearchQueryMapper {
    SearchQuery readBytes(byte[] searchQuery) throws IOException;
    byte[] convertQuery(SearchQuery searchQuery);
    byte[] convertQueryResult(List<TourDto> result);
}