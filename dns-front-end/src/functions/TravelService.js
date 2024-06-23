import axios from "axios";
// import { useNavigate } from "react-router-dom";
import { toast } from 'react-toastify';


const travelURL = 'http://localhost:8084/travels'

const createTravel = (travel) => {
    
    console.log(travel)
     return axios.post(`${travelURL}`, travel.travel,{

     }).then(response=>{
        toast.success("Travel successfuly created!")

     }).catch((err) => {

        if (err.response.status === 403) {
  
          toast.error("Error 403");
  
        }else{

            throw new Error();
    
          }
    })
     ;
}

export default{
    createTravel
}