import React, { useState } from "react";
import { Card } from "../componants/login/Card";
import { CardHeader } from "../componants/login/CardHeader";
import { CardTitle } from "../componants/login/CardTitle";
import { CardContent } from "../componants/login/CardContent";
import { Form } from "react-router-dom";
import { Input } from "../componants/Input";
import { Button } from "../componants/Button";

export const LoginPage: React.FC = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleSubmit = () => {
    // event.preventDefault;
    console.log("Logging in with", { email, password });
  };

  return (
    <div className="flex min-h-screen items-center justify-center bg-gray-100 p-4">
      <Card className={"w-full max-w-sm shadow-lg"}>
        <CardHeader>
          <CardTitle className={"text-center text-2xl font-bold"}>
            Login
          </CardTitle>
        </CardHeader>
        <CardContent>
          <Form className="space-y-4" >
            <Input
              type="email"
              placeholder="Email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
            />
            <Input
              type="password"
              placeholder="Password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
            <Button type="submit" className="w-full">
              Login
            </Button>
          </Form>
        </CardContent>
      </Card>
    </div>
  );
};
