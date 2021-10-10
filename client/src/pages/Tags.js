import React, { useEffect, useState } from "react";
import { Container } from "reactstrap";
import Crud from "../components/Crud";

export default function Tags(props) {
  return (
    <Container>
      <Crud
        url="/api/tags"
        tableColumns={["Id", "Name"]}
        getTableRowItems={(record) => [record.id, record.name]}
        formInputs={[
          { key: "name", label: "Name" },
        ]}
      />
    </Container>
  );
}
