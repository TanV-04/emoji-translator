-- Insert emoji mappings for PLACES category
INSERT IGNORE INTO emoji_mappings (id, emojis, answer, hint, category, difficulty, points, alternative_answers, active) VALUES
(1, '🗼🇫🇷', 'Eiffel Tower', 'Famous landmark in Paris', 'PLACES', 'EASY', 10, 'Paris Tower,Tour Eiffel', true),
(2, '🗽🇺🇸', 'Statue of Liberty', 'Symbol of freedom in New York', 'PLACES', 'EASY', 10, 'Lady Liberty', true),
(3, '🕌🇮🇳', 'Taj Mahal', 'White marble monument in India', 'PLACES', 'MEDIUM', 15, 'Taj', true),
(4, '🏰🇩🇪', 'Neuschwanstein Castle', 'Fairy tale castle in Germany', 'PLACES', 'HARD', 20, 'Neuschwanstein', true),
(5, '🌉🇺🇸', 'Golden Gate Bridge', 'Red bridge in San Francisco', 'PLACES', 'MEDIUM', 15, 'Golden Gate', true),
(6, '🗿🗻', 'Easter Island', 'Island with mysterious stone heads', 'PLACES', 'HARD', 20, 'Rapa Nui', true),
(7, '🏔️🇨🇭', 'Swiss Alps', 'Mountain range in Switzerland', 'PLACES', 'MEDIUM', 15, 'Alps', true),
(8, '🏛️🇬🇷', 'Acropolis', 'Ancient citadel in Athens', 'PLACES', 'HARD', 20, 'Parthenon', true),
(9, '🏖️🏝️', 'Maldives', 'Tropical paradise islands', 'PLACES', 'MEDIUM', 15, 'Maldives Islands', true),
(10, '🌋🇯🇵', 'Mount Fuji', 'Sacred mountain in Japan', 'PLACES', 'MEDIUM', 15, 'Fuji,Mt Fuji', true),
(11, '🏜️🇪🇬', 'Egyptian Pyramids', 'Ancient wonders in Egypt', 'PLACES', 'EASY', 10, 'Pyramids,Giza', true),
(12, '🌃🗽', 'New York City', 'The Big Apple', 'PLACES', 'EASY', 10, 'NYC,New York', true),
(13, '🏔️🇳🇵', 'Mount Everest', 'Highest mountain in the world', 'PLACES', 'MEDIUM', 15, 'Everest', true),
(14, '🏰🏴󐁧󐁢󐁥󐁮󐁧󐁿', 'Big Ben', 'Clock tower in London', 'PLACES', 'EASY', 10, 'Big Ben Tower', true),
(15, '🌊🏄', 'Hawaii', 'Surfing paradise', 'PLACES', 'EASY', 10, 'Hawaiian Islands', true);

-- Insert emoji mappings for FAMOUS_PERSONALITIES category
INSERT IGNORE INTO emoji_mappings (id, emojis, answer, hint, category, difficulty, points, alternative_answers, active) VALUES
(16, '🎸👨‍🎤⚡', 'Elvis Presley', 'King of Rock and Roll', 'FAMOUS_PERSONALITIES', 'MEDIUM', 15, 'Elvis,The King', true),
(17, '🎨👂', 'Vincent van Gogh', 'Dutch painter who cut his ear', 'FAMOUS_PERSONALITIES', 'MEDIUM', 15, 'Van Gogh,Gogh', true),
(18, '🍎💻', 'Steve Jobs', 'Co-founder of Apple', 'FAMOUS_PERSONALITIES', 'EASY', 10, 'Jobs', true),
(19, '⚽👑🇦🇷', 'Lionel Messi', 'Argentine football legend', 'FAMOUS_PERSONALITIES', 'EASY', 10, 'Messi', true),
(20, '🎤👑', 'Michael Jackson', 'King of Pop', 'FAMOUS_PERSONALITIES', 'EASY', 10, 'MJ,Jackson', true),
(21, '🔬💡', 'Albert Einstein', 'Theory of relativity', 'FAMOUS_PERSONALITIES', 'MEDIUM', 15, 'Einstein', true),
(22, '🎬🦇', 'Christopher Nolan', 'Director of Batman trilogy', 'FAMOUS_PERSONALITIES', 'HARD', 20, 'Nolan', true),
(23, '🎭📝', 'William Shakespeare', 'Romeo and Juliet author', 'FAMOUS_PERSONALITIES', 'MEDIUM', 15, 'Shakespeare', true),
(24, '👑💎', 'Queen Elizabeth', 'Longest reigning British monarch', 'FAMOUS_PERSONALITIES', 'EASY', 10, 'Elizabeth II', true),
(25, '🎤🐝', 'Beyonce', 'Queen Bey', 'FAMOUS_PERSONALITIES', 'EASY', 10, 'Beyonce Knowles', true),
(26, '🏀23', 'Michael Jordan', 'Basketball legend number 23', 'FAMOUS_PERSONALITIES', 'EASY', 10, 'Jordan,MJ', true),
(27, '🎬🕷️', 'Stan Lee', 'Creator of Marvel superheroes', 'FAMOUS_PERSONALITIES', 'MEDIUM', 15, 'Lee', true),
(28, '🎨🖼️', 'Pablo Picasso', 'Cubism pioneer', 'FAMOUS_PERSONALITIES', 'HARD', 20, 'Picasso', true),
(29, '🚀🌙', 'Neil Armstrong', 'First man on the moon', 'FAMOUS_PERSONALITIES', 'MEDIUM', 15, 'Armstrong', true),
(30, '🎬🦖', 'Steven Spielberg', 'Director of Jurassic Park', 'FAMOUS_PERSONALITIES', 'MEDIUM', 15, 'Spielberg', true);

-- Insert emoji mappings for FOOD category
INSERT IGNORE INTO emoji_mappings (id, emojis, answer, hint, category, difficulty, points, alternative_answers, active) VALUES
(31, '🍕🇮🇹', 'Pizza', 'Italian flat bread with toppings', 'FOOD', 'EASY', 10, 'Pizzas', true),
(32, '🍔🍟', 'Burger and Fries', 'American fast food combo', 'FOOD', 'EASY', 10, 'Hamburger,Burger', true),
(33, '🍣🇯🇵', 'Sushi', 'Japanese rice with fish', 'FOOD', 'EASY', 10, 'Sushi Roll', true),
(34, '🌮🇲🇽', 'Tacos', 'Mexican folded tortilla', 'FOOD', 'EASY', 10, 'Taco', true),
(35, '🍝🇮🇹', 'Pasta', 'Italian noodles', 'FOOD', 'EASY', 10, 'Spaghetti', true),
(36, '🥐🇫🇷', 'Croissant', 'French flaky pastry', 'FOOD', 'MEDIUM', 15, 'Croissants', true),
(37, '🍜🇨🇳', 'Ramen', 'Asian noodle soup', 'FOOD', 'EASY', 10, 'Noodles', true),
(38, '🥙🇹🇷', 'Kebab', 'Turkish grilled meat', 'FOOD', 'MEDIUM', 15, 'Kebabs,Shawarma', true),
(39, '🍛🇮🇳', 'Curry', 'Indian spicy dish', 'FOOD', 'EASY', 10, 'Indian Curry', true),
(40, '🧁🎂', 'Cupcake', 'Small individual cake', 'FOOD', 'EASY', 10, 'Cupcakes', true),
(41, '🥗🥬', 'Salad', 'Healthy green vegetables', 'FOOD', 'EASY', 10, 'Green Salad', true),
(42, '🍦🍨', 'Ice Cream', 'Frozen sweet dessert', 'FOOD', 'EASY', 10, 'Icecream', true),
(43, '☕🥐', 'Coffee and Croissant', 'French breakfast', 'FOOD', 'MEDIUM', 15, 'French Breakfast', true),
(44, '🍕🍝', 'Italian Food', 'Pizza and pasta combo', 'FOOD', 'EASY', 10, 'Italian Cuisine', true),
(45, '🥘🍲', 'Paella', 'Spanish rice dish', 'FOOD', 'HARD', 20, 'Spanish Paella', true);