import DarkModeToggle from "./DarkModeToggle";
import { useEffect, useState } from "react";
import AuthButtons from "./AuthButtons";
import { useNavigate } from "react-router";
import AdPostModal from "../modals/AdPostModal";


const Navbar = () => {
    const [isMenuOpen,setIsMenuOpen] = useState<boolean>(false)
    const [isAddModalOpen,setIsAddModalOpen] = useState<boolean>(false)


    const navigate = useNavigate()
    useEffect(() => {
        const handleResize = () => {
            if (window.innerWidth >= 768 && isMenuOpen) {
                setIsMenuOpen(false);
            }
        };

        window.addEventListener('resize', handleResize);
        return () => window.removeEventListener('resize', handleResize);
    }, [isMenuOpen]);

    useEffect(() => {
        setIsMenuOpen(false);
 
    }, [navigate]);


    return (
        <>
        {isAddModalOpen && <AdPostModal setIsModalOpen={setIsAddModalOpen}/>}
        {isMenuOpen && (
                        
                        <div className="w-[60%] md:hidden z-[39] border-2 border-purple-600  fixed top-[9rem] left-[20%] bg-[rgb(89,100,224)] shadow-lg shadow-purple-500 rounded-lg">
                            <div className="flex flex-col items-center py-4 space-y-4">
                            <AuthButtons setIsModalOpen={setIsAddModalOpen}/>
                            </div>
                        </div>
                    )}
        <div className="fixed top-0 left-0 right-0 z-20"> 
            <div className="relative bg-waves bg-no-repeat bg-cover h-[8rem] tablet:rounded-bl-full">
                <div className="absolute inset-0 w-full bg-[rgb(89,100,224)]/70 tablet:rounded-bl-full">
                    <div className="hidden md:flex flex-row-reverse pt-4 justify-center">
                        <AuthButtons  setIsModalOpen={setIsAddModalOpen}/>
                    </div>
                    <button 
                        onClick={() => setIsMenuOpen(!isMenuOpen)}
                        className="block md:hidden absolute right-1/2 translate-x-1/2 top-1/2 p-4 -translate-y-1/2 z-20 text-white cursor-pointer hover:bg-white/20 duration-500 rounded-2xl"
                    >
                        <div className="space-y-2">
                            <span className={`block w-8 h-0.5 bg-white transition-all duration-300 ${isMenuOpen ? 'rotate-45 translate-y-2.5' : ''}`}></span>
                            <span className={`block w-8 h-0.5 bg-white transition-all duration-300 ${isMenuOpen ? 'opacity-0' : ''}`}></span>
                            <span className={`block w-8 h-0.5 bg-white transition-all duration-300 ${isMenuOpen ? '-rotate-45 -translate-y-2.5' : ''}`}></span>
                        </div>
                    </button>
                    <div className="absolute text-white font-semibold text-2xl left-[8%] top-1/2 -translate-y-1/2">
                        <p className="cursor-default">devjobs</p>
                    </div>
                    <div className="absolute right-[8%] top-1/2 -translate-y-1/2">
                        <DarkModeToggle/>
                    </div>
                </div>
            </div>
        </div>
        </>
    );
}
export default Navbar;