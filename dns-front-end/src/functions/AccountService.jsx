import axios from "axios";
// import { useNavigate } from "react-router-dom";
import { toast } from 'react-toastify';

const userURL = 'http://localhost:8082/api';



const register =  (user) => {
 
  return new Promise( (resolve, reject) => {
       axios.post(`${userURL}/register`, user.user, {
      }).then(response => {
          console.log('kur', response.data);
          resolve(response.data);
          //navigate('/login')
  
      }).catch((err) => {
  
          if (err.response.status === 500) {
  
              reject(toast.error("Email taken or wrong information entered! Please try again later!"));
  
  
          }
          else {
            reject({err});

          }
      });
  })
  }

    export default  {
        register
    }