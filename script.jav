<!DOCTYPE html>
<html lang="ar">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>خلايا النحل</title>
    <style>
        /* تصميم الصفحة */
        body {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
            margin: 0;
            background-color: #000000;
            font-family: Arial, sans-serif;
        }

        /* تصميم خلية النحل */
        .honeycomb-button {
            width: 100px;
            height: 100px;
            background-color: white; /* لون الخلفية الأبيض */
            clip-path: polygon(
                50% 0%, 100% 25%, 100% 75%, 50% 100%, 0% 75%, 0% 25%
            ); /* شكل خلية النحل */
            border: none; /* إزالة الحدود */
            color: black; /* لون الحروف */
            font-size: 20px;
            font-weight: bold;
            cursor: pointer;
            margin: 1px; /* مسافة بين الخلايا */
            transition: background-color 0.3s ease;
        }

        /* تصميم الصفوف */
        .row {
            display: flex;
            margin-bottom: -25px; /* مسافة 0.1 بكسل بين الصفوف  */
        }

        /* تحريك الصف الثاني والرابع ليكونوا موازيين للمسافة بين الخلايا */
        .row:nth-child(2),
        .row:nth-child(4) {
            margin-left: 51px; /* تحريك الصفوف بمقدار نصف المسافة بين الخلايا */
        }
    </style>
</head>
<body>

    </body>

    <div id="honeycombGrid"></div>

    <script>
        // الأبجدية العربية
        const arabicLetters = "أبتثجحخدذرزسشصضطظعغفقكلمنهوي";

        // الألوان المتاحة
        const colors = ["orange", "red", "green", "white"];

        // إنشاء الشبكة
        const grid = document.getElementById("honeycombGrid");

        // خلط الحروف بشكل عشوائي
        const shuffledLetters = arabicLetters.split("").sort(() => Math.random() - 0.5);

        // إنشاء 5 صفوف
        for (let i = 0; i < 5; i++) {
            const row = document.createElement("div");
            row.className = "row";

            // إنشاء 5 أزرار في كل صف
            for (let j = 0; j < 5; j++) {
                const button = document.createElement("button");
                button.className = "honeycomb-button";

                // تعيين حرف غير مكرر
                const letter = shuffledLetters[i * 5 + j];
                button.textContent = letter;

                // تغيير اللون عند النقر
                button.addEventListener("click", () => {
                    // الحصول على اللون الحالي
                    const currentColor = button.style.backgroundColor;

                    // تحديد اللون التالي
                    let nextColor;
                    if (currentColor === "orange") {
                        nextColor = "red";
                    } else if (currentColor === "red") {
                        nextColor = "green";
                    } else if (currentColor === "green") {
                        nextColor = "white";
                    } else {
                        nextColor = "orange"; // العودة إلى البرتقالي إذا كان اللون أبيض
                    }

                    // تطبيق اللون الجديد
                    button.style.backgroundColor = nextColor;
                });

                row.appendChild(button);
            }

            grid.appendChild(row);
        }
    </script>

</body>
</html>

<!DOCTYPE html>
<html lang="ar">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>زر كبير بدون خلفية</title>
    <style>
        /* تخصيص مظهر الزر */
        .refresh-button {
            font-size: 24px; /* حجم النص */
            padding: 15px 30px; /* الحشو الداخلي (أعلى/أسفل - يمين/يسار) */
            background: none; /* إزالة الخلفية */
            border: 2px solid #007bff00; /* إطار الزر */
            color: #fefefe; /* لون النص */
            cursor: pointer; /* تغيير شكل المؤشر إلى يد */
            border-radius: 5px; /* زوايا مدورة */
            transition: background 0.3s, color 0.3s; /* تأثيرات عند التمرير */
        }

        /* تأثير عند تمرير الماوس فوق الزر */
        .refresh-button:hover {
            background: #007bff00; /* تغيير لون الخلفية */
            color: white; /* تغيير لون النص */
        }
    </style>
</head>
<body>
    <h1></h1>
    
    <!-- زر التحديث -->
    <button class="refresh-button" onclick="refreshPage()">مرحلة جديدة</button>

    <script>
        // دالة لتحديث الصفحة
        function refreshPage() {
            location.reload();
        }
    </script>
</body>
</html>