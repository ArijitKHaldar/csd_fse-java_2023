import { useEffect, useState } from "react";
import axios from "axios";

function IncomeByMonthTable({ userId }) {
  const [incomeData, setIncomeData] = useState([]);
  const [month, setMonth] = useState(1);

  useEffect(() => {
    axios
      .get(`/api/income/v1/userid/${userId}/month/${month}`)
      .then((response) => {
        setIncomeData(response.data);
      })
      .catch((error) => {
        console.log("Error fetching income data:", error);
      });
  }, [month, userId]);

  const handleMonthChange = (e) => {
    setMonth(Number(e.target.value));
  };

  return (
    <div className="table-section">
      <div className="all-income income-section">
        <h2>Income</h2>
        <div className="month-dropdown">
          <label htmlFor="month">Select Month: </label>
          <select id="month" value={month} onChange={handleMonthChange}>
            <option value={1}>January</option>
            <option value={2}>February</option>
            <option value={3}>March</option>
            <option value={4}>April</option>
            <option value={5}>May</option>
            <option value={6}>June</option>
            <option value={7}>July</option>
            <option value={8}>August</option>
            <option value={9}>September</option>
            <option value={10}>October</option>
            <option value={11}>November</option>
            <option value={12}>December</option>
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

export default IncomeByMonthTable;
