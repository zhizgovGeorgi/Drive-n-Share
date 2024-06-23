
import { useEffect } from "react";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import AccountService from "../functions/AccountService";
import { toast } from "react-toastify";

export default function Profile() {
    const navigate = useNavigate();
    const [id, setId] = useState('')
    const [userId, setUserId] = useState('')
    const [user, setUser] = useState(null);
    const email = sessionStorage.getItem("email");
    const [firstName, setFName]=useState('');
    const [lastName, setLName]=useState('');
    const [adress, setAddress]=useState('');
    const [password, setPassword] = useState('');
    const role = sessionStorage.getItem("role");
    
    const updateUserDetails=()=>{
      const userRequest={email, firstName, lastName, adress,  password}
      AccountService.updateUserDetails({userRequest})
    }
    
    const getUser = () =>
      AccountService.getUser(email);
    
    const deleteUser = () =>{
        const userDelete = {id, userId};
        // const deletionRequest = { id: token, username: username };
        AccountService.deleteUser(userDelete)
        navigate('/login')
      }
        // console.log(userDelete)

        
      
        
  useEffect(()=>{
   
    if (role !== "Driver" && role !== "Drivee" ) {
    navigate("/");
     
    }
    
      getUser().then(res => {
        console.log(res.data);
        setId(res.data.id)
        setUserId(res.data.id)
        setUser(res.data)
        setFName(res.data.fname)
setLName(res.data.lname)
        setAddress(res.data.adress)
        setPassword(res.data.password)
      })
  },[])

 

 

  return (
    
    <div className="userInfo" >
      {user &&
        <div key={user.id}>
  
   
      <div className='registerproperties'>
      <div className='bigName'>
  <h1>Your profile information</h1>
</div>
    <label for="fname">First Name:</label>
    <input value={firstName} onChange={(e)=>setFName(e.target.value)} type="text" id="firstName" name="firstName"/>
    </div>
    <div className='registerproperties'>
    <label for="lname">Last Name:</label>
    <input value={lastName} onChange={(e)=>setLName(e.target.value)} type="text" id="lastName" name="lastName"/>
    </div>
    <div className='registerproperties'>
    <label for="email">Email:</label>
    <input value={email} disabled type="text" id="email" name="email"/>
    </div>
    <div className='registerproperties'>
    <label for="lname">Address:</label>
    <input value={adress} onChange={(e)=>setAddress(e.target.value)} type="text" id="adress" name="adress"/>
    </div>
      <div className='registerproperties'>
    <label for="password">Password:</label>
    <input value={password} onChange={(e)=>setPassword(e.target.value)} type="password" id="password" name="password"/>
    </div>
      
<button  onClick={updateUserDetails}>
 Update
</button>
<button  onClick={deleteUser}>
 Delete
</button>
<div>
<input type="file" multiple="multiple" name="fileUpload"/>
</div>
         
        </div>
      }
    </div>
  );

}