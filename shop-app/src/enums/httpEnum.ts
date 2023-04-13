/**
 * @description: Request
 */
export enum RequestEnum {
  GET = 'GET',
  POST = 'POST',
  PATCH = 'PATCH',
  PUT = 'PUT',
  DELETE = 'DELETE'
}

/**
 * @description: Content Type
 */
export enum ContentTypeEnum {
  // json
  JSON = 'application/json',
  // json
  TEXT = 'text/plain',
  // form-data
  FORM_URLENCODED = 'application/x-www-form-urlencoded',
  // form-data  upload
  FORM_DATA = 'multipart/form-data'
}
