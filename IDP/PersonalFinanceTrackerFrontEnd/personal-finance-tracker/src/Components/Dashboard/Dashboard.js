import { useLocation, useNavigate } from "react-router-dom";
import "./Dashboard.css";
import React from "react";

function Dashboard() {
  const location = useLocation();
  const searchParams = new URLSearchParams(location.search);
  const userId = searchParams.get("userId");
  const navigate = useNavigate();

  const handleLogout = () => {
    navigate("/", { replace: true });
  };

  return (
    <div id="dashboard">
      <div className="navbar">
        <div className="navbar-left">
          <a href="/dashboard">Home</a>
          <div className="dropdown">
            <button className="dropbtn">Income</button>
            <div className="dropdown-content">
              <a href="/income">All Income</a>
              <a href="/income/month">Income for Month</a>
              <a href="/income/year">Income for Year</a>
              <a href="/income/insert">Insert Income</a>
              <a href="/income/update">Update Income</a>
              <a href="/income/delete">Delete Income</a>
            </div>
          </div>
          <div className="dropdown">
            <button className="dropbtn">Expenditure</button>
            <div className="dropdown-content">
              <a href="/expenditure">All Expenditure</a>
              <a href="/expenditure/date">Expenditure for Date</a>
              <a href="/expenditure/month">Expenditure for Month</a>
              <a href="/expenditure/year">Expenditure for Year</a>
              <a href="/expenditure/insert">Insert Expenditure</a>
              <a href="/expenditure/update">Update Expenditure</a>
              <a href="/expenditure/delete">Delete Expenditure</a>
            </div>
          </div>
        </div>
        <div className="navbar-right">
          <button className="logout-button" onClick={handleLogout}>
            Logout
          </button>
        </div>
      </div>

      <div className="overview-section">
        <h2>Overview</h2>
        {/* Display summary of income, expenditure, and savings */}
        {/* Show percentage of monthly and yearly savings */}
        {/* Include a progress bar for savings completion */}
      </div>

      <div className="income-section">
        <h2>Income</h2>
        <div className="income-list">
          {/* Display list of income entries */}
          {/* Include options to filter by month or year */}
        </div>
        <div className="income-form">
          {/* Create form for adding new income */}
          {/* Include fields for date, amount, description */}
        </div>
      </div>

      <div className="expenditure-section">
        <h2>Expenditure</h2>
        <div className="expenditure-list">
          {/* Display list of expenditure entries */}
          {/* Include options to filter by date, month or year */}
        </div>
        <div className="expenditure-form">
          {/* Create form for adding new expenditure */}
          {/* Include fields for date, amount, description, category */}
        </div>
      </div>

      <div className="savings-prediction">
        {/* Show predicted savings for next month */}
      </div>

      <div className="savings-completion">
        {/* Display percentage of completion for total savings */}
      </div>
    </div>
  );
}

export default Dashboard;
