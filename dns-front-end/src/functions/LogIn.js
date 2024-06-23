import axios from "axios";
import { toast } from "react-toastify";
import { jwtDecode } from "jwt-decode";
import { json } from "react-router-dom";
import AccountService from "./AccountService";

export default async function login({ username, password }) {
  
    var qs = require("qs");



  var config = {

    method: "post",

    url: "http://127.0.0.1:5000/login",

    headers: {

      "Content-Type": "application/json",

    },

    data:({

      username,

      password,

    }),

  };
  return await axios(config)
 

    .then((response) =>       { 
    sessionStorage.setItem("accessToken", response.data.access_token) 
    sessionStorage.setItem("refreshToken", response.data.refresh_token)
    sessionStorage.setItem("email", jwtDecode(response.data.access_token).email) 
    // let user = AccountService.getUser(jwtDecode(response.data.access_token).email)
    // console.log("kurv",user)
    // sessionStorage.setItem("role", response.data.role) 

    window.location.reload();
  } )

    .catch((err) => {

      if (err.response.status === 401) {

        alert(toast.error("The email and the password do not match"));

      }else{

        throw new Error();

      }

    });

}