import React, {useState} from 'react';
import AccountService from '../functions/AccountService';
import axios from 'axios';
import { Outlet } from 'react-router-dom';
import { toast } from 'react-toastify';
import { Link, useNavigate } from 'react-router-dom';


// const useStyles = makeStyles((theme) => ({
//   root: {
//     '& > *': {
//       margin: theme.spacing(1)
//     },
//   },
// }));

export default function Register() {
  
//   const navigate = useNavigate();
  
    // const paperStyle = {padding: '50px 20px', width:600, margin:"20px auto "}
  const [firstName, setFName]=useState('');
  const [lastName, setLName]=useState('');
  const [address, setAddress]=useState('');
  const [email, setEmail]=useState('');
  const [password, setPassword]=useState('');
  const role = 'Driver';
//     // const classes = useStyles();
const register =  () =>{
    const user={firstName, lastName, address, email, password, role}
    console.log(firstName);
    console.log(lastName);
    console.log(address);
    console.log(email);
    console.log(password);

  
     AccountService.register({user}).then(res=>{
      // navigate('/login') 
      toast.success("Successful registration! Time to log in!")
      // navigate("/login")
    })
}  
    
  
 
  
   return (
    <div className='registerPanel'>
    <div className='registerproperties'>
    <label for="fname">First name:</label>
    <input onChange={(e)=>setFName(e.target.value)} type="text" id="fname" name="fname"/>
    </div>
    <div className='registerproperties'>
    <label for="lname">Last name:</label>
    <input onChange={(e)=>setLName(e.target.value)} type="text" id="lname" name="lname"/>
    </div>
    <div className='registerproperties'>
    <label for="email">Email:</label>
    <input onChange={(e)=>setEmail(e.target.value)} type="text" id="email" name="email"/>
    </div>
    <div className='registerproperties'>
    <label for="address">Address:</label>
    <input onChange={(e)=>setAddress(e.target.value)} type="text" id="address" name="address"/>
    </div>
    <div className='registerproperties'>
    <label for="password">Password:</label>
    <input onChange={(e)=>setPassword(e.target.value)} type="text" id="password" name="password"/>
    </div>

    <div className='registerButton'>
        <button onClick={register}>
            Register
        </button>
    </div>
    </div>
   );
}


