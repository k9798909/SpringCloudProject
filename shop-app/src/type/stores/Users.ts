export default interface Users {
  username: string
  name: string
  token: string
}

export function clean(users: Users): void {
  users.username = ''
  users.name = ''
  users.token = ''
}
