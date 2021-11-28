import React from "react";
import { Table } from "reactstrap";
import { objectFetch } from "../api/ApiHelper";
import { ButtonModal } from "./ButtonModal";

export default function ManyToManySelector({ buttonText, items, selectedItems, postUrl, onChange }) {
  const itemsWithSelections = items.map((item) => {
    selectedItems.find(r => r.id === item.id) ? item.isSelected = true : item.isSelected = false
    return item;
  });

  const handleAddClick = (item) => {
    objectFetch(`${postUrl}/${item.id}`, "POST").then(() => onChange());
  };

  const handleRemoveClick = (item) => {
    objectFetch(`${postUrl}/${item.id}`, "DELETE").then(() => onChange());
  };

  return (
    <ButtonModal text={buttonText} className="btn btn-success">
      <div className="px-3 py-3">
        <Table>
          <tbody>
            {itemsWithSelections.map((item) => (
              <tr key={item.id}>
                <td>{item.name}</td>
                <td>
                  {!item.isSelected ? (
                    <button onClick={() => handleAddClick(item)} className="btn btn-primary">
                      Add
                    </button>
                  ) : (
                    <button onClick={() => handleRemoveClick(item)} className="btn btn-danger">
                      Remove
                    </button>
                  )}
                </td>
              </tr>
            ))}
          </tbody>
        </Table>
      </div>
    </ButtonModal>
  );
}
