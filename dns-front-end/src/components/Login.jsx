import React, {useState} from 'react';
import AccountService from '../functions/AccountService';
import axios from 'axios';
import { Outlet } from 'react-router-dom';
import { toast } from 'react-toastify';
import { Link, useNavigate } from 'react-router-dom';
import login from '../functions/LogIn';
import { useEffect } from 'react';

// const useStyles = makeStyles((theme) => ({
//   root: {
//     '& > *': {
//       margin: theme.spacing(1)
//     },
//   },
// }));

export default function Login() {
  
  const navigate = useNavigate();
  useEffect(()=>{
    const role = sessionStorage.getItem("role");
    if (sessionStorage.length!= 0 ) {
     navigate('/');     
    }
  },[])
  
    // const paperStyle = {padding: '50px 20px', width:600, margin:"20px auto "}
  const [username, setUsername]=useState('');
  const [password, setPassword]=useState('');
//     // const classes = useStyles();
const logIn = async()=>{
    console.log(username);
    console.log(password);
    await login({username, password}).then(res=>{
        navigate("/profile");
toast.success("Welcome!")
      })
      const user = AccountService.getUser(sessionStorage.getItem("email"))
      sessionStorage.setItem("role", (await user).data.role)
      // console.log("log ni user", (await user).data)
  
    
}  
    
  
 
  
   return (
    <div className='loginpanel'>
<div className='bigName'>
  <h1>Log In</h1>
</div>
    <div className='loginproperties'>
    <label for="email">Email:</label>
    <input onChange={(e)=>setUsername(e.target.value)} type="text" id="email" name="email"/>
    </div>
    <div className='loginproperties'>
    <label for="password">Password:</label>
    <input onChange={(e)=>setPassword(e.target.value)} type="password" id="password" name="password"/>
    </div>

    <div className='loginButton'>
        <button onClick={logIn}>
            Log in
        </button>
    </div>
    </div>
   );
}


