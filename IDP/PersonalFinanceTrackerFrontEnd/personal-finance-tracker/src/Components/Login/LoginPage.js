import React, { useState } from "react";
import { RiEyeFill, RiEyeOffFill } from "react-icons/ri";
import axios from "axios";
import "./LoginPage.css";

axios.defaults.baseURL = "http://127.0.0.1:8080";

function LoginPage() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [, setUserId] = useState("");
  const [errorMessage, setErrorMessage] = useState("");
  const [emailValid, setEmailValid] = useState(false);
  const [passwordValid, setPasswordValid] = useState(false);
  const [showPassword, setShowPassword] = useState(false);

  const handleSignup = async () => {
    if (!emailValid && passwordValid) {
      setErrorMessage("Please enter valid email.");
      return;
    } else if (emailValid && !passwordValid) {
      setErrorMessage("Please enter valid password.");
      return;
    } else if (!emailValid || !passwordValid) {
      setErrorMessage("Please enter valid email and password.");
      return;
    }

    try {
      let checkResponse;
      try {
        checkResponse = await axios.get(`/api/login/v1/email/${email}`);
      } catch (error) {
        if (error.response && error.response.status === 404) {
          const signupResponse = await axios.post("/api/login/v1", {
            emailId: email,
            password: password,
          });
          setUserId(signupResponse.data.userId);
          setErrorMessage(
            `Signup successful with User ID: ${signupResponse.data.userId}`
          );
          return; // Exit the function to prevent reaching the catch block
        }
        throw error; // Re-throw the error if it's not a 404 response
      }

      if (checkResponse.data.userId) {
        setErrorMessage("User already signed up. Please sign in.");
      }
    } catch (error) {
      setUserId("");
      setErrorMessage(error.response.data);
    }
  };

  const handleSignin = async () => {
    if (!emailValid && passwordValid) {
      setErrorMessage("Please enter valid email.");
      return;
    } else if (emailValid && !passwordValid) {
      setErrorMessage("Please enter valid password.");
      return;
    } else if (!emailValid || !passwordValid) {
      setErrorMessage("Please enter valid email and password.");
      return;
    }

    try {
      const response = await axios.get(`/api/login/v1/email/${email}`);
      if (!response.data) {
        setUserId("");
        setErrorMessage("User is not signed up. Please sign up first.");
      } else {
        if (response.data.password === password) {
          setUserId(response.data.userId);
          setErrorMessage("Successfully logged in");
        } else {
          setUserId("");
          setErrorMessage("Password does not match.");
        }
      }
    } catch (error) {
      setUserId("");
      setErrorMessage("User is not signed up. Please sign up first.");
    }
  };

  const validateEmail = (value) => {
    const regex = /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/;
    setEmail(value);
    setEmailValid(regex.test(value));
  };

  const validatePassword = (value) => {
    setPassword(value);
    if (value.length < 8) {
      setPasswordValid(false);
      return;
    }
    const hasLowercase = /[a-z]/.test(value);
    const hasUppercase = /[A-Z]/.test(value);
    const hasDigit = /\d/.test(value);
    const hasSpecial = /[!@#$%^&*()_+{}:"<>?|[\];',./`~\\=-]/.test(value);
    setPasswordValid(hasLowercase && hasUppercase && hasDigit && hasSpecial);
  };

  const togglePasswordVisibility = () => {
    setShowPassword(!showPassword);
  };

  return (
    <div className="login-page">
      <h1>Join Now!</h1>
      <div className="form-group">
        <label>Email:</label>
        <div className={`input-container ${emailValid ? "valid" : "invalid"}`}>
          <input
            type="text"
            value={email}
            placeholder="Enter your email id here"
            title="name@domain.com"
            onChange={(e) => validateEmail(e.target.value)}
          />
          <div className={`status-circle ${emailValid ? "green" : "red"}`} />
        </div>
      </div>
      <div className="form-group">
        <label>Password:</label>
        <div
          className={`input-container ${passwordValid ? "valid" : "invalid"}`}
        >
          <input
            type={showPassword ? "text" : "password"}
            value={password}
            placeholder="Enter your password here"
            title="Minimum 8 characters long with at least one Upper Case, Lower Case, Number & Special Character"
            onChange={(e) => validatePassword(e.target.value)}
          />
          <div className={`status-circle ${passwordValid ? "green" : "red"}`} />
          <button
            className="password-toggle-button"
            onClick={togglePasswordVisibility}
          >
            {showPassword ? <RiEyeOffFill /> : <RiEyeFill />}
          </button>
        </div>
      </div>
      <div className="button-group">
        <button className="signup-button" onClick={handleSignup}>
          Sign Up
        </button>
        <button className="signin-button" onClick={handleSignin}>
          Sign In
        </button>
      </div>
      {errorMessage && <p className="error-message">{errorMessage}</p>}
    </div>
  );
}

export default LoginPage;
