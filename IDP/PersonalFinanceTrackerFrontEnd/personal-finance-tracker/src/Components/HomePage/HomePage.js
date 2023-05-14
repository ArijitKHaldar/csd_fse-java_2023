import React from "react";
import LoginPage from "../Login/LoginPage";
import "./HomePage.css";

function HomePage() {
  return (
    <div className="homepage">
      <header>
        <h1 className="project-title">Personal Finance Tracker</h1>
        <p>Manage your finances with ease</p>
      </header>
      <main>
        <div className="widget">
          <LoginPage />
        </div>
      </main>
      <footer>
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
