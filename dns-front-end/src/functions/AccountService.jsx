import axios from "axios";
// import { useNavigate } from "react-router-dom";
import { toast } from 'react-toastify';

const userURL = 'http://localhost:8082/api';

const register =  (user) => {
         return axios.post(`${userURL}/register`, user.user, {
        }).then(response => {
            console.log('kur', response.data); 
            //navigate('/login')
    
        })
    }

    export default  {
        register
    }