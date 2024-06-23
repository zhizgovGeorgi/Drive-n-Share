import React, {useState} from 'react';
import AccountService from '../functions/AccountService';
import axios from 'axios';
import { Outlet } from 'react-router-dom';
import { toast } from 'react-toastify';
import { Link, useNavigate } from 'react-router-dom';
import TravelService from '../functions/TravelService';


// const useStyles = makeStyles((theme) => ({
//   root: {
//     '& > *': {
//       margin: theme.spacing(1)
//     },
//   },
// }));

export default function ManageTravels() {

      
  const navigate = useNavigate();
  
    // const paperStyle = {padding: '50px 20px', width:600, margin:"20px auto "}
  const [startPoint, setStartPoint]=useState('');
  const [endPoint, setEndPoint]=useState('');
  const [pricePerPerson, setPricePerPerson]=useState('');
  const [driverId, setDriverid]=useState('');
  const [departureDate, setDepartureDate]=useState('');
  const date = "01-01-2023";
//     // const classes = useStyles();
const createTravel =  () =>{
    AccountService.getUser(sessionStorage.getItem("email")).then(res => {
        setDriverid(res.data.id)
      })
    const travel={startPoint, endPoint, pricePerPerson, driverId, date }

  
     TravelService.createTravel(travel);
    }

 
    
  
 
  
   return (
    <div className='registerPanel'>
      <div className='bigName'>
  <h1>Create a new travel and share the experience</h1>
</div>
    <div className='registerproperties'>
    <label for="fname">Start Point:</label>
    <input onChange={(e)=>setStartPoint(e.target.value)} type="text" id="fname" name="fname"/>
    </div>
    <div className='registerproperties'>
    <label for="lname">End Point:</label>
    <input onChange={(e)=>setEndPoint(e.target.value)} type="text" id="lname" name="lname"/>
    </div>
    <div className='registerproperties'>
    <label for="email">Price Per Person:</label>
    <input onChange={(e)=>setPricePerPerson(e.target.value)} type="number" id="email" name="email"/>
    </div>
    <div className='registerproperties'>
    <label for="adess">Departure Date:</label>
    <input onChange={(e)=>setDepartureDate(e.target.value)} type="date" id="adress" name="adress"/>
    </div>
    

    <div className='registerButtondiv'>
        <button className='registerButton' onClick={createTravel}>
            Create Travel
        </button>
    </div>
    </div>
   );
}



