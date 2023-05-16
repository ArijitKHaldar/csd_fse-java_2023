import React, { useState, useRef, useEffect } from "react";
import LoginPage from "../Login/LoginPage";
import PopupBox from "./PopupBox";
import "./HomePage.css";

function HomePage() {
  const [showLogin, setShowLogin] = useState(false);
  const [showPopup, setShowPopup] = useState(false);
  const [popupContent, setPopupContent] = useState("");
  const loginBoxRef = useRef(null);

  const handleGetStarted = () => {
    setShowLogin(true);
  };

  const handleDismissLogin = (event) => {
    if (loginBoxRef.current && !loginBoxRef.current.contains(event.target)) {
      setShowLogin(false);
    }
  };

  const handleOpenPopup = (content) => {
    if (content === "About") {
      setPopupContent(
        <span className="popup-about">Created by Arijit Kumar Haldar</span>
      );
    } else if (content === "Contact") {
      setPopupContent(
        <span className="popup-contact">arijitkrhaldar@gmail.com</span>
      );
    } else if (content === "Terms of Service") {
      setPopupContent(
        <span className="popup-terms">
          This application is made for the requirements of IDP in CSD 2023,
          Cognizant
        </span>
      );
    }
    setShowPopup(true);
  };

  const handleClosePopup = () => {
    setShowPopup(false);
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
            <button
              className="footer-btn"
              onClick={() => handleOpenPopup("About")}
            >
              About
            </button>
          </li>
          <li>
            <button
              className="footer-btn"
              onClick={() => handleOpenPopup("Contact")}
            >
              Contact
            </button>
          </li>
          <li>
            <button
              className="footer-btn"
              onClick={() => handleOpenPopup("Terms of Service")}
            >
              Terms of Service
            </button>
          </li>
        </ul>
      </footer>
      {showPopup && (
        <PopupBox content={popupContent} onClose={handleClosePopup} />
      )}
    </div>
  );
}

export default HomePage;
