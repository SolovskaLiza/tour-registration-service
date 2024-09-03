package sokolovska.yelizaveta.tourregistrationservice.service;

import sokolovska.yelizaveta.tourregistrationservice.model.SearchQuery;
import sokolovska.yelizaveta.tourregistrationservice.model.TourDto;

import java.util.List;

public interface TourSearchService{
    List<TourDto> search(SearchQuery query);
    List<String> fetchSuggestions(String query);
}
