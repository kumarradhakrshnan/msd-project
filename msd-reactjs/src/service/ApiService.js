import axios from 'axios';

const USER_API_BASE_URL = 'http://localhost:8080/hiveoutput';

//File to specify the apis we are going to consume and the corresponding URLs to be mapped to.


class ApiService {
    fetchAgeAvg() {
        return axios.get(USER_API_BASE_URL + '/select/1');
    }
    fetchGenderAvg() {
        return axios.get(USER_API_BASE_URL + '/select/2');
    }
}
export default new ApiService();