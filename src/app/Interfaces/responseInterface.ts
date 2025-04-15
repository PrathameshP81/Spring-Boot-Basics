export interface ResponseInterface {
    success: boolean;
    message: string;
    data: boolean;  // If `data` is a boolean, use `boolean` instead of `true`
  }