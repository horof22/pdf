document.getElementById('uploadForm').addEventListener('submit', async function(event) {
    event.preventDefault();
    
    const fileInput = document.getElementById('fileInput');
    const message = document.getElementById('message');
    
    if (fileInput.files.length > 0) {
        const file = fileInput.files[0];
        message.textContent = `تم تحميل الملف: ${file.name}`;
        
        const apiKey = 'YOUR_CLOUDCONVERT_API_KEY'; // استبدل بمفتاح API الخاص بك
        const formData = new FormData();
        formData.append('file', file);
        formData.append('targetformat', 'pdf');

        try {
            // إنشاء مهمة تحويل
            const createJobResponse = await fetch('https://api.cloudconvert.com/v2/jobs', {
                method: 'POST',
                headers: {
                    'Authorization': `Bearer ${apiKey}`,
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    tasks: {
                        'import-file': {
                            operation: 'import/upload'
                        },
                        'convert-file': {
                            operation: 'convert',
                            input: 'import-file',
                            output_format: 'pdf'
                        },
                        'export-file': {
                            operation: 'export/url',
                            input: 'convert-file'
                        }
                    }
                })
            });

            const createJobData = await createJobResponse.json();
            const jobId = createJobData.data.id;

            // تحميل الملف إلى CloudConvert
            const uploadUrl = createJobData.data.tasks[0].result.url;
            await fetch(uploadUrl, {
                method: 'PUT',
                body: file
            });

            // انتظار اكتمال التحويل
            let jobStatus;
            do {
                const jobStatusResponse = await fetch(`https://api.cloudconvert.com/v2/jobs/${jobId}`, {
                    headers: {
                        'Authorization': `Bearer ${apiKey}`,
                    }
                });
                const jobStatusData = await jobStatusResponse.json();
                jobStatus = jobStatusData.data.status;
                await new Promise(resolve => setTimeout(resolve, 1000)); // انتظر ثانية واحدة
            } while (jobStatus !== 'finished');

            // تنزيل الملف المحول
            const exportUrl = createJobData.data.tasks[2].result.files[0].url;
            message.textContent = 'تم تحويل الملف إلى PDF بنجاح!';
            window.open(exportUrl, '_blank'); // افتح ملف PDF في نافذة جديدة
        } catch (error) {
            console.error('Error:', error);
            message.textContent = 'حدث خطأ أثناء التحويل.';
        }
    } else {
        message.textContent = 'يرجى اختيار ملف أولاً.';
    }
});
