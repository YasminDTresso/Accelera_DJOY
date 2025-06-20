// Verifica se o token existe no localStorage
const token = localStorage.getItem("jwt_token");

// Se existir, define no header Authorization para todas as requisições do Axios
if (token) {
  axios.defaults.headers.common["Authorization"] = `Bearer ${token}`;
}

// Interceptor para garantir que o token seja aplicado em tempo de requisição:
axios.interceptors.request.use(
  (config) => {
    const tokenAtual = localStorage.getItem("jwt_token"); // recupera o token sempre que uma requisição é feita
    if (tokenAtual) {
      config.headers["Authorization"] = `Bearer ${tokenAtual}`;
    }
    return config;
  },
  (error) => Promise.reject(error)
);
