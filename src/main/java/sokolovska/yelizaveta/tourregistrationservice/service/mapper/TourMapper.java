package sokolovska.yelizaveta.tourregistrationservice.service.mapper;

import org.mapstruct.Mapper;
import sokolovska.yelizaveta.tourregistrationservice.model.Tour;
import sokolovska.yelizaveta.tourregistrationservice.model.TourDto;
import sokolovska.yelizaveta.tourregistrationservice.model.document.TourDocument;

@Mapper
public interface TourMapper {
    TourDto convert(Tour tour);
    Tour convertIntoEntity(TourDto tourDto);
    TourDto convert(TourDocument tourDocument);
    TourDocument convertIntoDocument(TourDto tourDto);
}
