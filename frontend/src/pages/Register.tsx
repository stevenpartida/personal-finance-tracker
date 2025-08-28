import { useForm } from "react-hook-form";
import api from "../api/client";

type Form = { name: string; email: string; password: string };

export default function Register() {
  const { register, handleSubmit, reset } = useForm<Form>();

  return (
    <div style={{ padding: 24 }}>
      <h1>Register</h1>
      <form
        onSubmit={handleSubmit(async (data) => {
          const res = await api.post("/users", data);
          // store userId temporarily so we can call X-User-Id routes
          localStorage.setItem("userId", res.data.id);
          alert("Registered! Saved userId for testing.");
          reset();
        })}
      >
        <input placeholder="Name" {...register("name", { required: true })} /><br />
        <input placeholder="Email" type="email" {...register("email", { required: true })} /><br />
        <input placeholder="Password" type="password" {...register("password", { required: true })} /><br />
        <button type="submit">Sign up</button>
      </form>
    </div>
  );
}