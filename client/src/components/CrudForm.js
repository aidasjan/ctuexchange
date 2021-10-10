import React from "react";
import { Form, FormGroup, Input, Label } from "reactstrap";

export default function CrudForm({ record, onSubmit, formInputs }) {
  return (
    <Form onSubmit={onSubmit}>
      <Input type="hidden" id="id" value={record?.id} />
      {formInputs.map((input) => (
        <FormGroup className="mt-2">
          {input.options ? (
            <>
              <Label>{input.label}</Label>
              <Input type="select" id={input.key}>
                {input.options.map((option) =>
                  record && input.findId(record) == option.value ? (
                    <option value={option.value} selected>
                      {option.label}
                    </option>
                  ) : (
                    <option value={option.value}>{option.label}</option>
                  )
                )}
              </Input>
            </>
          ) : (
            <>
              <Label>{input.label}</Label>
              <Input
                type="text"
                id={input.key}
                defaultValue={record ? record[input.key] : null}
              />
            </>
          )}
        </FormGroup>
      ))}
      <Input type="submit" value="Submit" className="btn btn-primary mt-3" />
    </Form>
  );
}
