import { useEffect, useState } from "react";
import axios from "axios";
import "./AllIncomeTable.css";

function AllIncomeTable({ userId }) {
  const [incomeData, setIncomeData] = useState([]);

  useEffect(() => {
    axios
      .get(`/api/income/v1/userid/${userId}`)
      .then((response) => {
        setIncomeData(response.data);
      })
      .catch((error) => {
        console.log("Error fetching income data:", error);
      });
  }, [userId]);

  return (
    <div className="table-section">
      <div className="all-income income-section">
        <h2>Income</h2>
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
                  <th>Category</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>No Data</td>
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

export default AllIncomeTable;
