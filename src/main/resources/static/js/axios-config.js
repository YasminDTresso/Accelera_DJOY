const token = localStorage.getItem("jwt_token");

if (token) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
}
console.log("Axios configurado com token:", token);