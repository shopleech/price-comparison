export interface ILoginInfo {
    email: string;
    password: string;
}

export interface IRegisterInfo {
    invitation: string;
    email: string;
    firstname: string;
    lastname: string;
    password: string;
    consent: boolean;
}
