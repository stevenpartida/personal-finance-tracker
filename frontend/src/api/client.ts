import axios from "axios";

const api = axios.create({
  baseURL: "http://localhost:8080/api",
});

api.interceptors.request.use((config) => {
  const userId = localStorage.getItem("userId");
  if (userId) config.headers["X-User-Id"] = userId;
  return config;
});

export default api;