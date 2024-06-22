import axios from "axios";
import { toast } from "react-toastify";
import jwtDecode from "jwt-decode";
import { json } from "react-router-dom";

export default async function login({ email, password }) {
  
    var qs = require("qs");



  var config = {

    method: "post",

    url: "http://127.0.0.1:5000/login",

    // headers: {

    //   "Content-Type": "application/x-www-form-urlencoded",

    // },

    // data: qs.stringify({

    //   email,

    //   password,

    // }),

  };
  return axios(config)
 

    .then((response) =>       { 
    sessionStorage.setItem("accessToken", response.data.access_token) 
    sessionStorage.setItem("refreshToken", response.data.refresh_token) 
    window.location.reload();
  } )

    .catch((err) => {

      if (err.response.status === 403) {

        alert(toast.error("The email and the password do not match"));

      }else{

        throw new Error();

      }

    });

}