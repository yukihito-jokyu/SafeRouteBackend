import axios from 'axios';
import { RouteRanking } from '../models/RouteRanking';

const API_URL = 'https://www.greatruns.com/wp-json/wp/v2/route_ranking_category?per_page=100';

interface WordPressCategory {
  id: number;
  name: string;
  description: string;
  // Add other properties if needed, but they are not used in this transformation
}

export const fetchRouteRankings = async (): Promise<RouteRanking[]> => {
  try {
    const response = await axios.get<WordPressCategory[]>(API_URL);
    const data = response.data;

    // Transform the data into an array of RouteRanking objects
    const routeRankings: RouteRanking[] = data.map((category) => ({
      id: category.id,
      name: category.name,
      description: category.description,
    }));

    return routeRankings;
  } catch (error) {
    console.error('Error fetching route rankings:', error);
    // Depending on error handling strategy, you might want to:
    // - Throw the error to be caught by the caller
    // - Return a default value (e.g., an empty array)
    // - Log the error to a monitoring service
    throw error; // Re-throwing for now, can be adjusted
  }
};
