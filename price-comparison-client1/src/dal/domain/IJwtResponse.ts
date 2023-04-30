/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
export interface IJwtResponse {
    token: string
    refreshToken: string
    firstname: string
    lastname: string
    roles: string
}
