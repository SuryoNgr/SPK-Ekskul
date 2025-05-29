-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jul 13, 2024 at 10:37 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.2.20

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_ekskul`
--

-- --------------------------------------------------------

--
-- Table structure for table `siswa`
--

CREATE TABLE `siswa` (
  `nisn` varchar(20) NOT NULL,
  `nama` varchar(25) NOT NULL,
  `kelamin` varchar(20) NOT NULL,
  `tgl_lahir` date NOT NULL,
  `kelas` varchar(25) NOT NULL,
  `no_tlp` varchar(20) NOT NULL,
  `alamat` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `siswa`
--

INSERT INTO `siswa` (`nisn`, `nama`, `kelamin`, `tgl_lahir`, `kelas`, `no_tlp`, `alamat`) VALUES
('202043502433', 'Ikhsan S', 'Pria', '2000-07-08', 'KELAS XII', '08955565524', 'GASS'),
('202043502445', 'Budi Doremi', 'Pria', '2002-07-08', 'KELAS X', '0895333181278', 'Susukan\n');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_bobot`
--

CREATE TABLE `tbl_bobot` (
  `b1` float DEFAULT NULL,
  `b2` float DEFAULT NULL,
  `b3` float DEFAULT NULL,
  `b4` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_bobot`
--

INSERT INTO `tbl_bobot` (`b1`, `b2`, `b3`, `b4`) VALUES
(0.3, 0.25, 0.25, 0.2);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_ekskul`
--

CREATE TABLE `tbl_ekskul` (
  `id_ekskul` int(11) NOT NULL,
  `nama_ekskul` varchar(25) NOT NULL,
  `jam` varchar(10) NOT NULL,
  `hari` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbl_ekskul`
--

INSERT INTO `tbl_ekskul` (`id_ekskul`, `nama_ekskul`, `jam`, `hari`) VALUES
(1, 'Paskibra', '13:00', 'Senin'),
(3, 'Futsal', '15:00', 'Selasa'),
(4, 'PMI', '14:00', 'Rabu'),
(6, 'Basket', '13:30', 'Kamis'),
(7, 'Volly', '16:00', 'Jum\'at'),
(8, 'Silat', '14:15', 'Jum\'at');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_kriteria`
--

CREATE TABLE `tbl_kriteria` (
  `id` int(11) NOT NULL,
  `nama` varchar(25) NOT NULL,
  `R` char(25) NOT NULL,
  `c1` float NOT NULL,
  `c2` float NOT NULL,
  `c3` float NOT NULL,
  `c4` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbl_kriteria`
--

INSERT INTO `tbl_kriteria` (`id`, `nama`, `R`, `c1`, `c2`, `c3`, `c4`) VALUES
(4, 'Budi Doremi', 'Paskibra', 4, 3, 1, 2),
(5, 'Budi Doremi', 'Futsal', 3, 2, 1, 4),
(6, 'Budi Doremi', 'Silat', 4, 1, 1, 4);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_peringkat`
--

CREATE TABLE `tbl_peringkat` (
  `id` int(11) NOT NULL,
  `nama` varchar(25) NOT NULL,
  `nama_ekskul` varchar(20) NOT NULL,
  `nilai` float DEFAULT NULL,
  `Peringkat` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_peringkat`
--

INSERT INTO `tbl_peringkat` (`id`, `nama`, `nama_ekskul`, `nilai`, `Peringkat`) VALUES
(14, 'Budi Doremi', 'Paskibra', 0.9, 1),
(15, 'Budi Doremi', 'Futsal', 0.841667, 2),
(16, 'Budi Doremi', 'Silat', 0.833333, 3);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_user`
--

CREATE TABLE `tbl_user` (
  `id` int(11) NOT NULL,
  `username` varchar(20) CHARACTER SET utf8mb4 NOT NULL,
  `password` varchar(50) CHARACTER SET utf8mb4 DEFAULT NULL,
  `role` varchar(50) CHARACTER SET utf8mb4 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_user`
--

INSERT INTO `tbl_user` (`id`, `username`, `password`, `role`) VALUES
(1, '202043502445', 'user123', 'siswa'),
(2, 'admin', 'admin', 'admin'),
(4, '202043502444', 'SekolahKita', 'siswa'),
(5, '202043502433', 'SekolahKita', 'siswa');

-- --------------------------------------------------------

--
-- Table structure for table `tb_alternatif`
--

CREATE TABLE `tb_alternatif` (
  `id` int(11) NOT NULL,
  `nisn` varchar(20) NOT NULL,
  `nama` varchar(25) NOT NULL,
  `nama_ekskul` varchar(25) NOT NULL,
  `c1` varchar(30) NOT NULL,
  `c2` varchar(30) NOT NULL,
  `c3` varchar(30) NOT NULL,
  `c4` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_alternatif`
--

INSERT INTO `tb_alternatif` (`id`, `nisn`, `nama`, `nama_ekskul`, `c1`, `c2`, `c3`, `c4`) VALUES
(7, '202043502445', 'Budi Doremi', 'Paskibra', 'Sangat Berminat', 'Berbakat', 'Tidak Berprestasi', 'Cukup Didukung Orang Tua'),
(8, '202043502445', 'Budi Doremi', 'Futsal', 'Berminat', 'Cukup Berbakat', 'Tidak Berprestasi', 'Sangat Didukung Orang Tua'),
(9, '202043502445', 'Budi Doremi', 'Silat', 'Sangat Berminat', 'Tidak Berbakat', 'Tidak Berprestasi', 'Sangat Didukung Orang Tua');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `siswa`
--
ALTER TABLE `siswa`
  ADD PRIMARY KEY (`nisn`);

--
-- Indexes for table `tbl_ekskul`
--
ALTER TABLE `tbl_ekskul`
  ADD PRIMARY KEY (`id_ekskul`);

--
-- Indexes for table `tbl_kriteria`
--
ALTER TABLE `tbl_kriteria`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_peringkat`
--
ALTER TABLE `tbl_peringkat`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_user`
--
ALTER TABLE `tbl_user`
  ADD PRIMARY KEY (`id`),
  ADD KEY `username` (`username`);

--
-- Indexes for table `tb_alternatif`
--
ALTER TABLE `tb_alternatif`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_ekskul`
--
ALTER TABLE `tbl_ekskul`
  MODIFY `id_ekskul` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `tbl_kriteria`
--
ALTER TABLE `tbl_kriteria`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `tbl_peringkat`
--
ALTER TABLE `tbl_peringkat`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `tbl_user`
--
ALTER TABLE `tbl_user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `tb_alternatif`
--
ALTER TABLE `tb_alternatif`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
