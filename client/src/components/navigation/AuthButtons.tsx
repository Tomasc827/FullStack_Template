import { useNavigate } from "react-router";
import { useAuth } from "../contexts/AuthContext";
import { useDataContext } from "../contexts/DataContext";
import UserAvatar from "../authentication/UserAvatar";
import { useEffect } from "react";


interface PropsModal {
  setIsModalOpen: (value: boolean) => void;
  setIsAvatarOpen: (value: boolean) => void;
}

const AuthButtons: React.FC<PropsModal> = ({ setIsModalOpen, setIsAvatarOpen }) => {
  const { isAuthenticated, logout, roles } = useAuth();
  const {
    timeoutForSuccess,
    windowSize: { width },
  } = useDataContext();
  const navigate = useNavigate();
  useEffect(() => {
    setIsModalOpen(false)
    setIsAvatarOpen(false)
  },[navigate])

  return (
    <>
      {!isAuthenticated ? (
        <>
          <button
            onClick={() => navigate("/login")}
            className="tomas-navbar-button"
          >
            Login
          </button>
          <button
            onClick={() => navigate("/registration")}
            className="tomas-navbar-button"
          >
            Register
          </button>
        </>
      ) : (
        <>
          <UserAvatar setIsAvatarOpen={setIsAvatarOpen} />
          {width < 768 && (
            <button
              onClick={() => {
                navigate("/");
                logout();
                timeoutForSuccess("Successfully logged out");
              }}
              className="tomas-navbar-button"
            >
              Logout
            </button>
          )}
          {roles.includes("ROLE_ADMIN") && (
            <button
              onClick={() => setIsModalOpen(true)}
              className="tomas-navbar-button"
            >
              Add Content
            </button>
          )}
        </>
      )}
      <button onClick={() => navigate("/")} className="tomas-navbar-button">
        Homepage
      </button>
    </>
  );
};
export default AuthButtons;
