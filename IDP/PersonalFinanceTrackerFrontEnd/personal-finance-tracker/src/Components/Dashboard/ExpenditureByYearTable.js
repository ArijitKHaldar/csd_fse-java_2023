import { useEffect, useState } from "react";
import axios from "axios";

function ExpenditureByYearTable({ userId }) {
  const [expenditureData, setExpenditureData] = useState([]);
  const [year, setYear] = useState(new Date().getFullYear());
  const [categoryData, setCategoryData] = useState([]);

  useEffect(() => {
    axios
      .get(`/api/expenditure/v1/userid/${userId}/year/${year}`)
      .then((response) => {
        setExpenditureData(response.data);
      })
      .catch((error) => {
        if (error.response && error.response.status === 404) {
          setExpenditureData([]);
        } else {
          console.log("Error fetching expenditure data:", error);
        }
      });

    axios
      .get(`/api/category/v1/view`)
      .then((response) => {
        setCategoryData(response.data);
      })
      .catch((error) => {
        if (error.response && error.response.status === 404) {
          setCategoryData([]);
        } else {
          console.log("Error fetching income data:", error);
        }
      });
  }, [year, userId]);

  const getCategoryTag = (categoryId) => {
    const category = categoryData.find(
      (category) => category.categoryId === categoryId
    );
    return category ? category.expenditureTag : "No Data";
  };

  const handleYearChange = (e) => {
    setYear(Number(e.target.value));
  };

  return (
    <div className="table-section">
      <div className="child-component expenditure-section">
        <h2>Expenditure</h2>
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
        <div className="expenditure-list">
          {expenditureData.length > 0 ? (
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
                {expenditureData.map((expenditure) => (
                  <tr key={expenditure.expenditureId}>
                    <td>{expenditure.expenditureId}</td>
                    <td>{expenditure.expenditureDate}</td>
                    <td>{expenditure.expenditureAmount}</td>
                    <td>{getCategoryTag(expenditure.categoryId)}</td>
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
export default ExpenditureByYearTable;
