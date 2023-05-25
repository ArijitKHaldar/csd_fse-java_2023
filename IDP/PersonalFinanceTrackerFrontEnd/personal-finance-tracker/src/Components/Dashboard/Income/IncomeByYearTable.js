import { useEffect, useState } from "react";
import axios from "axios";

function IncomeByYearTable({ userId }) {
  const [incomeData, setIncomeData] = useState([]);
  const [year, setYear] = useState(new Date().getFullYear());

  useEffect(() => {
    axios
      .get(`/api/income/v1/userid/${userId}/year/${year}`)
      .then((response) => {
        setIncomeData(response.data);
      })
      .catch((error) => {
        if (error.response && error.response.status === 404) {
          setIncomeData([]);
        } else {
          console.log("Error fetching income data:", error);
        }
      });
  }, [year, userId]);

  const handleYearChange = (e) => {
    setYear(Number(e.target.value));
  };

  return (
    <div className="table-section">
      <div className="child-component income-section">
        <h2>Income</h2>
        <div className="year-dropdown">
          <label htmlFor="year">Select Year: </label>
          <select id="year" value={year} onChange={handleYearChange}>
            <option value={new Date().getFullYear() - 2}>
              {new Date().getFullYear() - 2}
            </option>
            <option value={new Date().getFullYear() - 1}>
              {new Date().getFullYear() - 1}
            </option>
            <option value={new Date().getFullYear()}>
              {new Date().getFullYear()}
            </option>
            <option value={new Date().getFullYear() + 1}>
              {new Date().getFullYear() + 1}
            </option>
            <option value={new Date().getFullYear() + 2}>
              {new Date().getFullYear() + 2}
            </option>
          </select>
        </div>
        <div className="income-list">
          {incomeData.length > 0 ? (
            <table>
              <thead>
                <tr>
                  <th>Id</th>
                  <th>Date</th>
                  <th>Amount</th>
                </tr>
              </thead>
              <tbody>
                {incomeData.map((income) => (
                  <tr key={income.incomeId}>
                    <td>{income.incomeId}</td>
                    <td>{income.incomeDate}</td>
                    <td>{income.incomeAmount}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          ) : (
            <table>
              <thead>
                <tr>
                  <th>Id</th>
                  <th>Date</th>
                  <th>Amount</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>No Data</td>
                  <td>No Data</td>
                  <td>No Data</td>
                </tr>
              </tbody>
            </table>
          )}
        </div>
      </div>
    </div>
  );
}

export default IncomeByYearTable;
