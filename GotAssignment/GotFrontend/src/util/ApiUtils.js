
import axios from "axios";
const Base_url = "http://localhost:8080";

const GetRequests = async (path) => {

    let options = {
      url:`${Base_url+path}`,
      headers:{
        "content-type":"application/json"
      },
      method:"GET"
    }

    try{

      let res = await axios(options)
      if(res.status == 200){
        console.log(res.status)
      }  
      return res.data;
      
    }catch(error){
        console.log("Error "+error)
    }
  }

export {GetRequests};