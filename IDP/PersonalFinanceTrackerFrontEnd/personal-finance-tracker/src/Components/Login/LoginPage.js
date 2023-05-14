import React, { useState } from 'react';
import axios from 'axios';
import './LoginPage.css';

axios.defaults.baseURL = 'http://127.0.0.1:8080';

function LoginPage() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [userId, setUserId] = useState('');
  const [errorMessage, setErrorMessage] = useState('');

  const handleSignup = async () => {
    try {
      let checkResponse;
      try {
        checkResponse = await axios.get(`/api/login/v1/email/${email}`);
      } catch (error) {
        if (error.response && error.response.status === 404) {
          // Email ID not found, proceed with signup
          const signupResponse = await axios.post('/api/login/v1', {
            emailId: email,
            password: password
          });
          setUserId(signupResponse.data.userId);
          setErrorMessage(`Signup successful with User ID: ${signupResponse.data.userId}`);
          return; // Exit the function to prevent reaching the catch block
        }
        throw error; // Re-throw the error if it's not a 404 response
      }
  
      // Email ID already registered
      if(checkResponse.data.userId) {
        setErrorMessage('User already signed up. Please sign in.');
      }
    } catch (error) {
      setUserId('');
      setErrorMessage(error.response.data);
    }
  };
  

  const handleSignin = async () => {
    try {
      const response = await axios.get(`/api/login/v1/email/${email}`);
      if (!response.data) {
        setUserId('');
        setErrorMessage('User is not signed up. Please sign up first.');
      } else {
        if (response.data.password === password) {
          setUserId(response.data.userId);
          setErrorMessage('Successfully logged in');
        } else {
          setUserId('');
          setErrorMessage('Password does not match.');
        }
      }
    } catch (error) {
      setUserId('');
      setErrorMessage('User is not signed up. Please sign up first.');
    }
  };

  return (
    <div>
      <h1>Join Now!</h1>
      <div>
        <label>Email:</label>
        <input type="text" value={email} onChange={(e) => setEmail(e.target.value)} />
      </div>
      <div>
        <label>Password:</label>
        <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} />
      </div>
      <div>
        <button onClick={handleSignup}>Sign Up</button>
        <button onClick={handleSignin}>Sign In</button>
      </div>
      {errorMessage && <p>{errorMessage}</p>}
    </div>
  );
}

export default LoginPage;