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

export default function Login() {
  
//   const navigate = useNavigate();
  
    // const paperStyle = {padding: '50px 20px', width:600, margin:"20px auto "}
  const [email, setEmail]=useState('');
  const [password, setPassword]=useState('');
//     // const classes = useStyles();
const login =  () =>{
    const user={email, password}
    console.log(email);
    console.log(password);

  
     AccountService.register({user}).then(res=>{
      // navigate('/login') 
      toast.success("Successful registration! Time to log in!")
      // navigate("/login")
    })
}  
    
  
 
  
   return (
    <div className='loginpanel'>

    <div className='loginproperties'>
    <label for="email">Email:</label>
    <input onChange={(e)=>setEmail(e.target.value)} type="text" id="email" name="email"/>
    </div>
    <div className='loginproperties'>
    <label for="password">Password:</label>
    <input onChange={(e)=>setPassword(e.target.value)} type="password" id="password" name="password"/>
    </div>

    <div className='loginButton'>
        <button onClick={login}>
            Log in
        </button>
    </div>
    </div>
   );
}


