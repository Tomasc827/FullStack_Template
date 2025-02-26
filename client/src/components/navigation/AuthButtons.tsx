import { useNavigate } from "react-router";
import { useAuth } from "../contexts/AuthContext";
import { useDataContext } from "../contexts/DataContext";
import UserAvatar from "../authentication/UserAvatar";
import { useEffect, useState } from "react";
import CloseSvg from "../svgs/CloseSvg";

interface PropsModal {
  setIsModalOpen: (value: boolean) => void;
}

const AuthButtons: React.FC<PropsModal> = ({ setIsModalOpen }) => {
  const { isAuthenticated, logout, roles } = useAuth();
  const {
    timeoutForSuccess,
    windowSize: { width },
  } = useDataContext();
  const navigate = useNavigate();
  const [isAvatarOpen, setIsAvatarOpen] = useState<boolean>(false);
  useEffect(() => {
    setIsModalOpen(false)
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
          {isAvatarOpen && width >= 768 && (
            <div
              onMouseLeave={() => {
                setTimeout(() => {
                  setIsAvatarOpen(false);
                }, 1500);
              }}
              className="w-[12rem] flex flex-col justify-between items-center text-center z-[60] border-2 border-purple-600  fixed top-[5rem] left-[51.6%] bg-[rgb(89,100,224)] shadow-lg shadow-purple-500 rounded-lg"
            >
              <CloseSvg setIsAvatarOpen={setIsAvatarOpen} />
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
            </div>
          )}
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
