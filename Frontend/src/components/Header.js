/* src/components/Header.js */

import React from 'react';
import { Link } from 'react-router-dom'; // Import Link for navigation between routes

const Header = () => {
    return (
        <header className="header"> {/* Container for the header */}
            <nav> {/* Navigation section */}
                <ul> {/* Unordered list for navigation links */}
                    <li><Link to="/">Home</Link></li> {/* Link to the home page */}
                    {/* Ensure no logout button here */}
                </ul>
            </nav>
        </header>
    );
};

export default Header; // Export the Header component for use in other parts of the application
