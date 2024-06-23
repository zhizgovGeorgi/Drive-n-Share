import axios from "axios";
import { toast } from 'react-toastify';

const userURL = 'http://localhost:8082/api';
// const loginURL = 'http://127.0.0.1:5000/login';



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

  const getUser = (email) =>{
    return axios.post(`${userURL}/profile`, email, {
      params:{
  'email': email
      },
    //   headers:{
    //       'Authorization':"Bearer " + jwtToken
    //   }
  })
  }
  const deleteUser = (userDelete) => {
    return axios.delete(`${userURL}`, {
      headers: {
          'Content-Type': 'application/json'
      },
      data: JSON.stringify({
        id: userDelete.id,
        userId: userDelete.userId
      })
    }).then(response=>{
      console.log("idto",userDelete.id)
        console.log('kur',response.data)
toast.success('Successfull deletion of the user!');
window.location.reload();
    });
}

  const updateUserDetails = (userRequest) =>{
    return axios.put(`${userURL}`, userRequest.userRequest, {
    //   headers:{
    //       'Authorization':"Bearer " + jwtToken
    //   }
  }).then(response => {
    toast.success("Succesful update of your profile details!")
  
  })
  }

  const logout = () => {
    sessionStorage.clear();
    window.location.reload();
 }

//  const login = (email, password) => {
//     return new Promise( (resolve, reject) => {
//         axios.post(`${loginURL}`, (email.email, password.password), {
//        }).then(response => {
//            console.log('kur', response.data);
//            resolve(response.data);
//            //navigate('/login')
   
//        }).catch((err) => {
   
//            if (err.response.status === 500) {
   
//                reject(toast.error("Email taken or wrong information entered! Please try again later!"));
   
   
//            }
//            else {
//              reject({err});
 
//            }
//        });
//    })
//  }
 
 

    export default  {
        register,
        getUser,
        updateUserDetails,
        logout,
        deleteUser
    }