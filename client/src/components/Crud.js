import React, { useState, useEffect } from 'react';
import {
  Table,
  Container
} from 'reactstrap';
import { formFetch } from '../api/ApiHelper';
import { ButtonModal } from './ButtonModal';
import CrudForm from './CrudForm';

export default function Crud(props) {
  const { url, tableColumns, getTableRowItems, formInputs, renderManyToManySelector } = props;
  const [records, setRecords] = useState([]);

  const refreshTable = () => {
    fetch(url).then((response) => response.json().then((results) => {
      setRecords(results);
    }));
  }

  const handleDelete = (record) => {
    fetch(`${url}/${record.id}`, { method: "DELETE" }).then(() => refreshTable());
  }

  const handleAdd = (e) => {
    e.preventDefault();
    formFetch(`${url}`, "POST", e).then(() => refreshTable());
  }

  const handleUpdate = (e) => {
    e.preventDefault();
    const id = [...e.target].find(t => t.id == 'id').value;
    formFetch(`${url}/${id}`, "PUT", e).then(() => refreshTable());
  }

  useEffect(() => {
    refreshTable();
  }, []);

  return (
    <div>
      <ButtonModal text="Add New Item" className="btn btn-primary my-3">
        <Container className="py-3">
          <h3>Add Item</h3>
          <CrudForm formInputs={formInputs} onSubmit={handleAdd} />
        </Container>
      </ButtonModal>

      <Table className="">
        <tbody>
          <tr>
            {tableColumns.map((column) => (
              <th>{column}</th>
            ))}
            <th></th>
            <th></th>
            {renderManyToManySelector ? <th></th> : null}
          </tr>
          {records.map((record) => (
            <tr>
              {getTableRowItems(record).map((item) => (
                <td>{item}</td>
              ))}
              <td>
                <ButtonModal text="Edit" className="btn btn-primary">
                  <Container className="py-3">
                    <h3>Edit Item</h3>
                    <CrudForm
                      record={record}
                      formInputs={formInputs}
                      onSubmit={handleUpdate}
                    />
                  </Container>
                </ButtonModal>
              </td>
              <td>
                <button
                  className="btn btn-danger"
                  onClick={() => handleDelete(record)}
                >
                  Delete
                </button>
              </td>
              {renderManyToManySelector ? <td>{renderManyToManySelector(record, refreshTable)}</td> : null}
            </tr>
          ))}
        </tbody>
      </Table>
    </div>
  );
}