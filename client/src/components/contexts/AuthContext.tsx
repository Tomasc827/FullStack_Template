import { jwtDecode } from "jwt-decode";
import { createContext, ReactNode, useContext, useState } from "react";
type AuthContextType = {
    isAuthenticated: boolean;
    roles: string[];
    login: (token: string) => void;
    logout: () => void;
}


const AuthContext = createContext<AuthContextType>({
    isAuthenticated: false,
    roles: [],
    login: () => {},
    logout: () => {}
});

export const useAuth = () => {
  return useContext(AuthContext);
}

export const AuthProvider = ({children}: {children:ReactNode}) => {
    const [isAuthenticated,setIsAuthenticated] = useState<boolean>(!!localStorage.getItem("token"))
    const [roles,setRoles] = useState<string[]>(() => {
        const storedRoles = localStorage.getItem("roles");
        return storedRoles ? JSON.parse(storedRoles) : [];
    })

    const login = (token:string) => {
        localStorage.setItem("token",token);
        setIsAuthenticated(true)
        const decoded: any = jwtDecode(token);
        const scopeString: string = decoded.scope || "";
        const roleArray = scopeString.split(" ").filter(role => role.startsWith("ROLE_"))
        localStorage.setItem("roles",JSON.stringify(roleArray))
        setRoles(roleArray);
    } 

    const logout = () => {
        localStorage.clear();
        setIsAuthenticated(false);
        setRoles([]);
    }


    return (
        <AuthContext.Provider value={{login,logout,isAuthenticated,roles}} >
            {children}
        </AuthContext.Provider>
    )
}