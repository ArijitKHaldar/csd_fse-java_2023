import { useLocation, useNavigate } from "react-router-dom";
import "./Dashboard.css";
import React, { useState, useEffect } from "react";
import DashboardTable from "./DashboardTable";
import AllIncomeTable from "./AllIncomeTable";
import IncomeByMonthTable from "./IncomeByMonthTable";
import IncomeByYearTable from "./IncomeByYearTable";
import InsertIncomeTable from "./InsertIncomeTable";
import UpdateIncomeTable from "./UpdateIncomeTable";
import DeleteIncomeTable from "./DeleteIncomeTable";
import AllExpenditureTable from "./AllExpenditureTable";
import ExpenditureByDateTable from "./ExpenditureByDateTable";
import ExpenditureByMonthTable from "./ExpenditureByMonthTable";
import ExpenditureByYearTable from "./ExpenditureByYearTable";
import InsertExpenditureTable from "./InsertExpenditureTable";
import UpdateExpenditureTable from "./UpdateExpenditureTable";
import DeleteExpenditureTable from "./DeleteExpenditureTable";

function Dashboard() {
  const location = useLocation();
  const searchParams = new URLSearchParams(location.search);
  const userId = searchParams.get("userId");
  const navigate = useNavigate();

  useEffect(() => {
    if (!userId || userId === "") {
      navigate(`/`);
    }
  }, [userId, navigate]);

  const [incomeTable, setIncomeTable] = useState(false);
  const [incomeByMonth, setIncomeByMonth] = useState(false);
  const [incomeByYear, setIncomeByYear] = useState(false);
  const [insertIncome, setInsertIncome] = useState(false);
  const [updateIncome, setUpdateIncome] = useState(false);
  const [deleteIncome, setDeleteIncome] = useState(false);
  const [expenditureTable, setExpenditureTable] = useState(false);
  const [expenditureByDate, setExpenditureByDate] = useState(false);
  const [expenditureByMonth, setExpenditureByMonth] = useState(false);
  const [expenditureByYear, setExpenditureByYear] = useState(false);
  const [insertExpenditure, setInsertExpenditure] = useState(false);
  const [updateExpenditure, setUpdateExpenditure] = useState(false);
  const [deleteExpenditure, setDeleteExpenditure] = useState(false);

  const handleLogout = () => {
    navigate("/", { replace: true });
  };

  const handleLinkClick = (event) => {
    event.preventDefault();
    const link = event.target.getAttribute("href");
    switch (link) {
      case "/dashboard/income":
        setIncomeTable((prevState) => !prevState);
        setIncomeByMonth(false);
        setIncomeByYear(false);
        setInsertIncome(false);
        setUpdateIncome(false);
        setDeleteIncome(false);
        setExpenditureTable(false);
        setExpenditureByDate(false);
        setExpenditureByMonth(false);
        setExpenditureByYear(false);
        setInsertExpenditure(false);
        setUpdateExpenditure(false);
        setDeleteExpenditure(false);
        break;
      case "/dashboard/income/month":
        setIncomeTable(false);
        setIncomeByMonth((prevState) => !prevState);
        setIncomeByYear(false);
        setInsertIncome(false);
        setUpdateIncome(false);
        setDeleteIncome(false);
        setExpenditureTable(false);
        setExpenditureByDate(false);
        setExpenditureByMonth(false);
        setExpenditureByYear(false);
        setInsertExpenditure(false);
        setUpdateExpenditure(false);
        setDeleteExpenditure(false);
        break;
      case "/dashboard/income/year":
        setIncomeTable(false);
        setIncomeByMonth(false);
        setIncomeByYear((prevState) => !prevState);
        setInsertIncome(false);
        setUpdateIncome(false);
        setDeleteIncome(false);
        setExpenditureTable(false);
        setExpenditureByDate(false);
        setExpenditureByMonth(false);
        setExpenditureByYear(false);
        setInsertExpenditure(false);
        setUpdateExpenditure(false);
        setDeleteExpenditure(false);
        break;
      case "/dashboard/income/insert":
        setIncomeTable(false);
        setIncomeByMonth(false);
        setIncomeByYear(false);
        setInsertIncome((prevState) => !prevState);
        setUpdateIncome(false);
        setDeleteIncome(false);
        setExpenditureTable(false);
        setExpenditureByDate(false);
        setExpenditureByMonth(false);
        setExpenditureByYear(false);
        setInsertExpenditure(false);
        setUpdateExpenditure(false);
        setDeleteExpenditure(false);
        break;
      case "/dashboard/income/update":
        setIncomeTable(false);
        setIncomeByMonth(false);
        setIncomeByYear(false);
        setInsertIncome(false);
        setUpdateIncome((prevState) => !prevState);
        setDeleteIncome(false);
        setExpenditureTable(false);
        setExpenditureByDate(false);
        setExpenditureByMonth(false);
        setExpenditureByYear(false);
        setInsertExpenditure(false);
        setUpdateExpenditure(false);
        setDeleteExpenditure(false);
        break;
      case "/dashboard/income/delete":
        setIncomeTable(false);
        setIncomeByMonth(false);
        setIncomeByYear(false);
        setInsertIncome(false);
        setUpdateIncome(false);
        setDeleteIncome((prevState) => !prevState);
        setExpenditureTable(false);
        setExpenditureByDate(false);
        setExpenditureByMonth(false);
        setExpenditureByYear(false);
        setInsertExpenditure(false);
        setUpdateExpenditure(false);
        setDeleteExpenditure(false);
        break;
      case "/dashboard/expenditure":
        setIncomeTable(false);
        setIncomeByMonth(false);
        setIncomeByYear(false);
        setInsertIncome(false);
        setUpdateIncome(false);
        setDeleteIncome(false);
        setExpenditureTable((prevState) => !prevState);
        setExpenditureByDate(false);
        setExpenditureByMonth(false);
        setExpenditureByYear(false);
        setInsertExpenditure(false);
        setUpdateExpenditure(false);
        setDeleteExpenditure(false);
        break;
      case "/dashboard/expenditure/date":
        setIncomeTable(false);
        setIncomeByMonth(false);
        setIncomeByYear(false);
        setInsertIncome(false);
        setUpdateIncome(false);
        setDeleteIncome(false);
        setExpenditureTable(false);
        setExpenditureByDate((prevState) => !prevState);
        setExpenditureByMonth(false);
        setExpenditureByYear(false);
        setInsertExpenditure(false);
        setUpdateExpenditure(false);
        setDeleteExpenditure(false);
        break;
      case "/dashboard/expenditure/month":
        setIncomeTable(false);
        setIncomeByMonth(false);
        setIncomeByYear(false);
        setInsertIncome(false);
        setUpdateIncome(false);
        setDeleteIncome(false);
        setExpenditureTable(false);
        setExpenditureByDate(false);
        setExpenditureByMonth((prevState) => !prevState);
        setExpenditureByYear(false);
        setInsertExpenditure(false);
        setUpdateExpenditure(false);
        setDeleteExpenditure(false);
        break;
      case "/dashboard/expenditure/year":
        setIncomeTable(false);
        setIncomeByMonth(false);
        setIncomeByYear(false);
        setInsertIncome(false);
        setUpdateIncome(false);
        setDeleteIncome(false);
        setExpenditureTable(false);
        setExpenditureByDate(false);
        setExpenditureByMonth(false);
        setExpenditureByYear((prevState) => !prevState);
        setInsertExpenditure(false);
        setUpdateExpenditure(false);
        setDeleteExpenditure(false);
        break;
      case "/dashboard/expenditure/insert":
        setIncomeTable(false);
        setIncomeByMonth(false);
        setIncomeByYear(false);
        setInsertIncome(false);
        setUpdateIncome(false);
        setDeleteIncome(false);
        setExpenditureTable(false);
        setExpenditureByDate(false);
        setExpenditureByMonth(false);
        setExpenditureByYear(false);
        setInsertExpenditure((prevState) => !prevState);
        setUpdateExpenditure(false);
        setDeleteExpenditure(false);
        break;
      case "/dashboard/expenditure/update":
        setIncomeTable(false);
        setIncomeByMonth(false);
        setIncomeByYear(false);
        setInsertIncome(false);
        setUpdateIncome(false);
        setDeleteIncome(false);
        setExpenditureTable(false);
        setExpenditureByDate(false);
        setExpenditureByMonth(false);
        setExpenditureByYear(false);
        setInsertExpenditure(false);
        setUpdateExpenditure((prevState) => !prevState);
        setDeleteExpenditure(false);
        break;
      case "/dashboard/expenditure/delete":
        setIncomeTable(false);
        setIncomeByMonth(false);
        setIncomeByYear(false);
        setInsertIncome(false);
        setUpdateIncome(false);
        setDeleteIncome(false);
        setExpenditureTable(false);
        setExpenditureByDate(false);
        setExpenditureByMonth(false);
        setExpenditureByYear(false);
        setInsertExpenditure(false);
        setUpdateExpenditure(false);
        setDeleteExpenditure((prevState) => !prevState);
        break;
      default:
        setIncomeTable(false);
        setIncomeByMonth(false);
        setIncomeByYear(false);
        setInsertIncome(false);
        setUpdateIncome(false);
        setDeleteIncome(false);
        setExpenditureTable(false);
        setExpenditureByDate(false);
        setExpenditureByMonth(false);
        setExpenditureByYear(false);
        setInsertExpenditure(false);
        setUpdateExpenditure(false);
        setDeleteExpenditure(false);
        break;
    }
  };

  const renderTable = () => {
    if (incomeTable) {
      return <AllIncomeTable userId={userId} />;
    } else if (incomeByMonth) {
      return <IncomeByMonthTable userId={userId} />;
    } else if (incomeByYear) {
      return <IncomeByYearTable userId={userId} />;
    } else if (insertIncome) {
      return <InsertIncomeTable userId={userId} />;
    } else if (updateIncome) {
      return <UpdateIncomeTable userId={userId} />;
    } else if (deleteIncome) {
      return <DeleteIncomeTable userId={userId} />;
    } else if (expenditureTable) {
      return <AllExpenditureTable userId={userId} />;
    } else if (expenditureByDate) {
      return <ExpenditureByDateTable userId={userId} />;
    } else if (expenditureByMonth) {
      return <ExpenditureByMonthTable userId={userId} />;
    } else if (expenditureByYear) {
      return <ExpenditureByYearTable userId={userId} />;
    } else if (insertExpenditure) {
      return <InsertExpenditureTable userId={userId} />;
    } else if (updateExpenditure) {
      return <UpdateExpenditureTable userId={userId} />;
    } else if (deleteExpenditure) {
      return <DeleteExpenditureTable userId={userId} />;
    } else {
      return <DashboardTable userId={userId} />;
    }
  };

  return (
    <div id="dashboard-background">
      <div id="dashboard">
        <div className="navbar">
          <div className="navbar-left">
            <a href={`/dashboard?userId=${userId}`}>Dashboard</a>
            <div className="dropdown">
              <button className="dropbtn">Income</button>
              <div className="dropdown-content">
                <a href="/dashboard/income" onClick={handleLinkClick}>
                  All Income
                </a>
                <a href="/dashboard/income/month" onClick={handleLinkClick}>
                  Income for Month
                </a>
                <a href="/dashboard/income/year" onClick={handleLinkClick}>
                  Income for Year
                </a>
                <a href="/dashboard/income/insert" onClick={handleLinkClick}>
                  Insert Income
                </a>
                <a href="/dashboard/income/update" onClick={handleLinkClick}>
                  Update Income
                </a>
                <a href="/dashboard/income/delete" onClick={handleLinkClick}>
                  Delete Income
                </a>
              </div>
            </div>
            <div className="dropdown">
              <button className="dropbtn">Expenditure</button>
              <div className="dropdown-content">
                <a href="/dashboard/expenditure" onClick={handleLinkClick}>
                  All Expenditure
                </a>
                <a href="/dashboard/expenditure/date" onClick={handleLinkClick}>
                  Expenditure for Date
                </a>
                <a
                  href="/dashboard/expenditure/month"
                  onClick={handleLinkClick}
                >
                  Expenditure for Month
                </a>
                <a href="/dashboard/expenditure/year" onClick={handleLinkClick}>
                  Expenditure for Year
                </a>
                <a
                  href="/dashboard/expenditure/insert"
                  onClick={handleLinkClick}
                >
                  Insert Expenditure
                </a>
                <a
                  href="/dashboard/expenditure/update"
                  onClick={handleLinkClick}
                >
                  Update Expenditure
                </a>
                <a
                  href="/dashboard/expenditure/delete"
                  onClick={handleLinkClick}
                >
                  Delete Expenditure
                </a>
              </div>
            </div>
          </div>
          <div className="navbar-right">
            <button className="logout button" onClick={handleLogout}>
              Logout
            </button>
          </div>
        </div>
        {renderTable()}
      </div>
    </div>
  );
}

export default Dashboard;
