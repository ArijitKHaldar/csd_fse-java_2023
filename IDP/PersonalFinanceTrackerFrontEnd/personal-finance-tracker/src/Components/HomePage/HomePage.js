import React, { useState, useRef, useEffect } from "react";
import LoginPage from "../Login/LoginPage";
import "./HomePage.css";

function HomePage() {
  const [showLogin, setShowLogin] = useState(false);
  const loginBoxRef = useRef(null);

  const handleGetStarted = () => {
    setShowLogin(true);
  };

  const handleDismissLogin = (event) => {
    if (loginBoxRef.current && !loginBoxRef.current.contains(event.target)) {
      setShowLogin(false);
    }
  };

  useEffect(() => {
    document.addEventListener("mousedown", handleDismissLogin);
    return () => {
      document.removeEventListener("mousedown", handleDismissLogin);
    };
  }, []);

  return (
    <div className="homepage">
      <header className="header">
        <h1 className="project-title">Personal Finance Tracker</h1>
        <p className="subtitle">Manage your finances with ease</p>
      </header>
      <main>
        {showLogin ? (
          <div className="backdrop">
            <div className="login-box" ref={loginBoxRef}>
              <LoginPage />
            </div>
          </div>
        ) : (
          <div className="get-started" onClick={handleGetStarted}>
            <button className="get-started-button">Let's Get Started</button>
          </div>
        )}
      </main>
      <footer className="footer">
        <ul>
          <li>
            <a href="#about">About</a>
          </li>
          <li>
            <a href="#contact">Contact</a>
          </li>
          <li>
            <a href="#terms">Terms of Service</a>
          </li>
        </ul>
      </footer>
    </div>
  );
}

export default HomePage;