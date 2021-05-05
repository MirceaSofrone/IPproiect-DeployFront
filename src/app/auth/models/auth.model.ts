export interface LoginPayload{
  username: String;
  password: String;
}

export interface RegisterPayload {
  username: String;
  name: String;
  email: String;
  phone: String;
  password: String;
  role: String[];
}