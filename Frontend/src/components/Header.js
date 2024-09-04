/* src/components/Header.js */
import React from 'react';
import { Link } from 'react-router-dom';

const Header = () => {
    return (
        <header className="header">
            <nav>
                <ul>
                    <li><Link to="/">Home</Link></li>
                    {/* Ensure no logout button here */}
                </ul>
            </nav>
        </header>
    );
};

export default Header;