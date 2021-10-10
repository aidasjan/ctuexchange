import React from "react";
import { Modal, Col } from "reactstrap";

export const ButtonModal = (props) => {
  const { text, className } = props;
  const [isOpen, setIsOpen] = React.useState(false);

  const openModal = () => {
    setIsOpen(true);
  };

  const closeModal = () => {
    setIsOpen(false);
  };

  return (
    <div>
      <button className={className} onClick={openModal}>
        {text}
      </button>
      <Modal isOpen={isOpen} className="modal-form">
        {props.children}
        <Col>
          <button
            className="form-control btn btn-secondary"
            style={{ borderRadius: 0 }}
            onClick={closeModal}
          >
            Cancel
          </button>
        </Col>
      </Modal>
    </div>
  );
};
