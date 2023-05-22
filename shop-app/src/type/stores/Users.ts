export default interface Users {
  name: string
  token: string
}

export function clean(users: Users): void {
  users.name = ''
  users.token = ''
}
