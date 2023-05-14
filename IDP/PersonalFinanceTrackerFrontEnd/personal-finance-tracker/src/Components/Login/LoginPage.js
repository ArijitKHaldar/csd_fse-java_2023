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
      const response = await axios.post('/api/login/v1', {
        emailId: email,
        password: password
      });
      setUserId(response.data.userId);
      setErrorMessage('');
    } catch (error) {
      setUserId('');
      setErrorMessage(error.response.data);
    }
  };

  const handleSignin = async () => {
    try {
      const response = await axios.get(`/api/login/v1/email/${email}`);
      setUserId(response.data.userId);
      setErrorMessage('');
    } catch (error) {
      setUserId('');
      setErrorMessage('User not found. Please sign up.');
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
      {userId && <p>Successfully signed up with User ID: {userId}</p>}
    </div>
  );
}

export default LoginPage;